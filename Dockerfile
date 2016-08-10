FROM    jboss/wildfly:9.0.1.Final

ADD site/target/site-1.0-SNAPSHOT.war \
/opt/jboss/wildfly/standalone/deployments/ROOT.war
