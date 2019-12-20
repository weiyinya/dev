package com.wy.activiticore;

import com.wy.activiticore.examples.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.api.task.runtime.events.TaskAssignedEvent;
import org.activiti.api.task.runtime.events.TaskCompletedEvent;
import org.activiti.api.task.runtime.events.listener.TaskRuntimeEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class ActivitiCoreApplication implements CommandLineRunner {

	@Autowired
	private SecurityUtil securityUtil;

	@Autowired
	private TaskRuntime taskRuntime;

	public static void main(String[] args) {
		SpringApplication.run(ActivitiCoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Using Security Util to simulate a logged in user
		securityUtil.logInAs("salaboy");

		// Let's create a Group Task (not assigned, all the members of the group can claim it)
		//  Here 'salaboy' is the owner of the created task
		log.info("> Creating a Group Task for 'activitiTeam'");
		taskRuntime.create(TaskPayloadBuilder.create()
				.withName("First Team Task")
				.withDescription("This is something really important")
				.withCandidateGroup("activitiTeam")
				.withPriority(10)
				.build());

		// Let's log in as 'other' user that doesn't belong to the 'activitiTeam' group
		securityUtil.logInAs("other");

		// Let's get all my tasks (as 'other' user)
		log.info("> Getting all the tasks");
		Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));

		// No tasks are returned
		log.info(">  Other cannot see the task: " + tasks.getTotalItems());


		// Now let's switch to a user that belongs to the activitiTeam
		securityUtil.logInAs("erdemedeiros");

		// Let's get 'erdemedeiros' tasks
		log.info("> Getting all the tasks");
		tasks = taskRuntime.tasks(Pageable.of(0, 10));

		// 'erdemedeiros' can see and claim the task
		log.info(">  erdemedeiros can see the task: " + tasks.getTotalItems());


		String availableTaskId = tasks.getContent().get(0).getId();

		// Let's claim the task, after the claim, nobody else can see the task and 'erdemedeiros' becomes the assignee
		log.info("> Claiming the task");
		taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(availableTaskId).build());


		// Let's complete the task
		log.info("> Completing the task");
		taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(availableTaskId).build());


	}

	@Bean
	public TaskRuntimeEventListener<TaskAssignedEvent> taskAssignedListener() {
		return taskAssigned -> log.info(">>> Task Assigned: '"
				+ taskAssigned.getEntity().getName() +
				"' We can send a notification to the assginee: " + taskAssigned.getEntity().getAssignee());
	}

	@Bean
	public TaskRuntimeEventListener<TaskCompletedEvent> taskCompletedListener() {
		return taskCompleted -> log.info(">>> Task Completed: '"
				+ taskCompleted.getEntity().getName() +
				"' We can send a notification to the owner: " + taskCompleted.getEntity().getOwner());
	}

}
