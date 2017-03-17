package mbean;

import javax.management.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by lzz on 17/3/17.
 */
public class PersonDynamic implements DynamicMBean{
    /**
     * person对象就是我们需要管理的Bean
     */
    private Person person = new Person();
    /**
     * 描述属性信息
     */
    private List<MBeanAttributeInfo> attributes = new ArrayList<MBeanAttributeInfo>();
    /**
     * 描述构造器信息
     */
    private List<MBeanConstructorInfo> constructors = new ArrayList<MBeanConstructorInfo>();
    /**
     * 描述方法信息
     */
    private List<MBeanOperationInfo> operations = new ArrayList<MBeanOperationInfo>();
    /**
     * 描述通知信息
     */
    private List<MBeanNotificationInfo> notifications = new ArrayList<MBeanNotificationInfo>();
    /**
     * MBeanInfo用于管理以上描述信息
     */
    private MBeanInfo mBeanInfo;
    public PersonDynamic() {
        try{
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 初始化方法
     * @since 2010-9-15 上午11:35:18
     * @throws Exception
     */
    private void init() throws Exception{
        //构建需要被管理的属性，方法等消息
        buildDynamicInfo();
        //创建一个MBeanInfo对象
        mBeanInfo = createMBeanInfo();
    }
    /**
     *
     * 构建需要被管理的属性，方法, 构造器等消息.  具体api可以去参考相关文档。
     * @since 2010-9-15 上午11:33:39
     * @throws Exception
     */
    private void buildDynamicInfo() throws Exception{
        constructors.add(new MBeanConstructorInfo("PersonDynamic()构造器",getPerson().getClass().getConstructors()[0]));
        attributes.add(new MBeanAttributeInfo("name", "java.lang.String", "姓名", true, true, false));
        attributes.add(new MBeanAttributeInfo("gender", "java.lang.String", "性别", true, true, false));
        attributes.add(new MBeanAttributeInfo("age", "int", "年龄", true, true, false));
        operations.add(new MBeanOperationInfo("toString()方法.",getPerson().getClass().getMethod("toString",null)));
        operations.add(new MBeanOperationInfo("printName()方法.",getPerson().getClass().getMethod("printName",null)));
        operations.add(new MBeanOperationInfo("say()方法.",getPerson().getClass().getMethod("say",new Class[]{String.class})));
    }
    /**
     *
     * 创建MBeanInfo对象
     * @since 2010-9-15 上午11:37:51
     * @return
     */
    private MBeanInfo createMBeanInfo(){
        return new MBeanInfo(this.getClass().getName(),
                "PersonDynamic",
                attributes.toArray(new MBeanAttributeInfo[attributes.size()]),
                constructors.toArray(new MBeanConstructorInfo[constructors.size()]),
                operations.toArray(new MBeanOperationInfo[operations.size()]),
                notifications.toArray(new MBeanNotificationInfo[notifications.size()])
        );
    }
    /**
     * 获取person对象属性值
     */
    public Object getAttribute(String attribute) {
        return getPerson().getName();
    }
    /**
     * 通过属性名称获取一个属性对象列表
     */
    public AttributeList getAttributes(String[] attributes) {
        if(attributes == null || attributes.length == 0) {
            return null;
        }
        try{
            AttributeList attrList = new AttributeList();
            for (String attrName : attributes) {
                Object obj = this.getAttribute(attrName);
                Attribute attribute = new Attribute(attrName, obj);
                attrList.add(attribute);
            }
            return attrList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取MBeanInfo
     */
    public MBeanInfo getMBeanInfo() {
        return mBeanInfo;
    }
    /**
     * 通过反射调用person里面的方法
     */
    public Object invoke(String actionName, Object[] params, String[] signature)
            throws MBeanException, ReflectionException {
        try{
            Method methods[] = getPerson().getClass().getMethods();
            for (Method method : methods) {
                String name = method.getName();
                if(name.equals(actionName)){
                    Object result = method.invoke(getPerson(), params);
                    if(result != null){
                        System.out.println(result);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 设置属性值
     */
    public void setAttribute(Attribute attribute) {
        getPerson().setName(attribute.getName());
    }
    /**
     * 批量设置属性值
     */
    @SuppressWarnings("unchecked")
    public AttributeList setAttributes(AttributeList attributes) {
        if(attributes == null || attributes.isEmpty()){
            return attributes;
        }
        try{
            for (Iterator it = attributes.iterator(); it.hasNext();){
                Attribute attribute = (Attribute) it.next();
                setAttribute(attribute);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attributes;
    }
    /*==========================属性方法======================*/
    public void setPerson(Person person) {
        this.person = person;
    }
    public Person getPerson() {
        return person;
    }
}
