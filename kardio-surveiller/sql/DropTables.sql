SET SQL_SAFE_UPDATES = 0;

DROP TABLE API_STATUS
DROP TABLE APP_LOOKUP
DROP TABLE CONTAINER_STATS
DROP TABLE APP_ROLE
DROP TABLE COUNTER_METRIC
DROP TABLE ENV_COUNTER
DROP TABLE COUNTER
DROP TABLE COUNTER_METRIC_TYPE
DROP TABLE ALERT_SUBSCRIPTION
DROP TABLE APP_SESSION
DROP TABLE COMPONENT_MESSAGE
DROP TABLE DAILLY_COMP_STATUS
DROP TABLE HEALTH_CHECK_PARAM
DROP TABLE COMP_FAILURE_LOG
DROP TABLE HEALTH_CHECK
DROP TABLE ENVIRONMENT
DROP TABLE HEALTH_CHECK_TYPE
DROP TABLE STATUS
DROP TABLE REGION
DELETE FROM COMPONENT WHERE PARENT_COMPONENT_ID IS NOT NULL;
COMMIT;
DROP TABLE COMPONENT
DROP TABLE COMPONENT_TYPE