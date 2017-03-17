package mbean;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created by lzz on 17/3/17.
 */
public class App {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        // 创建MBeanServer
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        // 新建MBean ObjectName, 在MBeanServer里标识注册的MBean
        ObjectName name = new ObjectName("mbean:type=PersonDynamic");
        PersonDynamic mbean = new PersonDynamic();
        // 在MBeanServer里注册MBean, 标识为ObjectName(com.tenpay.jmx:type=Echo)
        mbs.registerMBean(mbean, name);

        Thread.sleep(Long.MAX_VALUE);
    }
}
