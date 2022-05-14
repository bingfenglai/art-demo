package pers.lbf.springbootactiviti.activititest;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.model.payloads.StartProcessPayload;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.ClaimTaskPayloadBuilder;
import org.activiti.api.task.model.builders.CompleteTaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lbf.springbootactiviti.utils.SecurityUtil;

import java.util.List;

/**
 * @author 赖柄沣 bingfengdev@aliyun.com
 * @version 1.0
 * @date 2020/8/18 12:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Activiti7SpringbootTest {

    @Autowired
    private ProcessRuntime processRuntime;
    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private ProcessEngine processEngine;

    @Before
    public void init() {
        //认证
        securityUtil.logInAs("system");
    }

    /**
     * 部署流程
     *
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-08-18 12:53:51
     * @version 1.0
     */
    @Test
    public void repositoryProcess() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("holiday.bpmn")
                .name("请假流程")
                .deploy();
        System.out.println(deployment.getName());
        System.out.println(deployment.getDeploymentTime());
    }

    /**
     * 分页查询系统中所有可用的流程定义
     *
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-08-18 13:32:36
     * @version 1.0
     */
    @Test
    public void findProcessList() {
        Page<ProcessDefinition> processDefinitionPage = processRuntime
                .processDefinitions(Pageable.of(0, 10));
        List<ProcessDefinition> content = processDefinitionPage.getContent();
        for (ProcessDefinition processDefinition : content) {
            System.out.println(processDefinition.getName());
        }
    }

    /**
     * 启动流程
     *
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-08-18 13:21:12
     * @version 1.0
     */
    @Test
    public void startProcess() {

        StartProcessPayload myProcess = ProcessPayloadBuilder
                .start()
                .withProcessDefinitionId("myProcess_1:1:5c5e0de3-e112-11ea-a73b-287fcf13e373")
                .build();

        myProcess.setProcessInstanceName("张三请假");
        processRuntime.start(myProcess);
        System.out.println("流程实例" + myProcess.getId());

    }

    /**
     * 查询并完成任务
     *
     * @author 赖柄沣 bingfengdev@aliyun.com
     * @date 2020-08-18 13:41:40
     * @version 1.0
     */
    @Test
    public void findAndFinishTask() {
        Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10));

        if (taskPage.getTotalItems() > 0) {

            List<Task> content = taskPage.getContent();
            for (Task task : content) {
                //拾取任务
                taskRuntime.claim(new ClaimTaskPayloadBuilder()
                        .withTaskId(task.getId())
                        .build());
                //完成任务
                taskRuntime.complete(new CompleteTaskPayloadBuilder()
                        .withTaskId(task.getId())
                        .build());
            }
        }

    }

}
