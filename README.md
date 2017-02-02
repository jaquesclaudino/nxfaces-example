# apache-tomee:
- copy lib/objectdb-jee.jar to apache-tomee-plus-7.0.2/lib

- copy lib/tomcat-objectdb-realm-0.0.1.jar to apache-tomee-plus-7.0.2/lib

- edit apache-tomee-plus-7.0.2/conf/server.xml:
~~~~
<Realm className="org.apache.catalina.realm.LockOutRealm 
	...
	<Realm className="com.objectdb.realm.ObjectDBRealm" 
		resourceName="example-realm" 
		url="$objectdb/db/example.odb"
		userEntity="User" 
		userNameColumn="login" 
		passwordColumn="password"
		groupNameColumn="groupName"/>
~~~~
			 
# glassfish:
- copy lib/objectdb-jee.jar to glassfish-4.1.1/glassfish/domains/domain1/lib

- copy lib/glassfish-objectdb-realm-0.0.1.jar to glassfish-4.1.1/glassfish/domains/domain1/lib

- edit glassfish-4.1.1/glassfish/domains/domain1/config/login.conf:
~~~~
objectdbRealm {
	com.objectdb.realm.LoginModule required;
};
~~~~
	
- edit glassfish-4.1.1/glassfish/domains/domain1/config/domain.xml:
~~~~
<config name="server-config">
	...
	<security-service>
	...
		<auth-realm classname="com.objectdb.realm.ObjectDBRealm" name="example-realm">
			<property name="group-entity" value="User"></property>
			<property name="user-name-column" value="login"></property>
			<property name="url" value="$objectdb/db/example.odb"></property>
			<property name="password-column" value="password"></property>
			<property name="user-entity" value="User"></property>
			<property name="group-name-column" value="groupName"></property>
		</auth-realm>
~~~~