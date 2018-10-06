package net.virgodirk.wildfire.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for WfJson
 *
 * @author 李晓勇 on 2017年8月25日 下午5:29:21
 * @version Version 3.0
 */
public class WfJsonTest {

    private Person person;
    private List<Person> personList;
    private String personJson;
    private String personListJson;

    @Before
    public void setUp() {
        person = new Person();
        person.setId("001");
        person.setName("李四");
        person.setGender("1");
        person.setBirthDate("1984-08-07");
        personJson = "{\"birthDate\":\"1984-08-07\",\"gender\":\"1\",\"id\":\"001\",\"name\":\"李四\"}";

        personList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Person p = new Person();
            p.setId(WfString.nextId("" + i, 3));
            p.setName("人员" + (i + 1));
            p.setGender(i % 2 == 0 ? "1" : "2");
            p.setBirthDate("1984-08-1" + String.valueOf(i));
            personList.add(p);
        }
        personListJson = "[{\"birthDate\":\"1984-08-10\",\"gender\":\"1\",\"id\":\"001\",\"name\":\"人员1\"},";
        personListJson += "{\"birthDate\":\"1984-08-11\",\"gender\":\"2\",\"id\":\"002\",\"name\":\"人员2\"},";
        personListJson += "{\"birthDate\":\"1984-08-12\",\"gender\":\"1\",\"id\":\"003\",\"name\":\"人员3\"}]";
    }
    
    @Test
    public void testToJsonString() {
        Assert.assertEquals("{}", WfJson.toJsonString(null));
        Assert.assertEquals(personJson, WfJson.toJsonString(person));
        Assert.assertEquals(personListJson, WfJson.toJsonString(personList));
    }

    @Test
    public void testParseObject() {
        Assert.assertEquals(person.getName(), WfJson.parseObject(personJson, Person.class).getName());
    }

    @Test
    public void testParseArray() {
        Assert.assertEquals(personList.get(0).getName(), WfJson.parseArray(personListJson, Person.class).get(0).getName());
    }
}