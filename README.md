# Angel Gallegos Assignment

Endpoints are protected with basic auth:
```
username=admin
password=password
```

To update the report service host and port change application properties values:
```
report-service.host_name=http://localhost
report-service.port=4000
```

There is fixture data being created on the Application runner if you don't
want new data to be created on each run you should comment this code.