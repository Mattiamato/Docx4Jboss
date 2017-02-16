README
======

Tested with jboss eap 6.4.7 and Java 1.8.0_112

Create a file named "module.xml" in {your-jboss-installation-directory}\modules\system\layers\base\org\docx4j-compat\main.
File content:

```
<module xmlns="urn:jboss:module:1.1" name="org.docx4j-compat">
	<dependencies>
        <module name="com.sun.xml.bind" export="true"/>
        <module name="javax.xml.jaxp-provider" export="true"/>
        <module name="org.apache.xalan" export="true"/>
        <module name="sun.jdk" export="true"/>
        <system export="true">
            <paths>
                <path name="com/sun/xml/internal/bind/marshaller"/>
            </paths>
        </system>
    </dependencies>
</module>
```

Deploy to your jboss server and call servlet "/HomeServlet.do".
Enjoy.
