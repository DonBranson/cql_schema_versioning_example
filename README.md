cql_schema_versioning_example
=============================

This is a quick example that I threw together very quickly, so beware. :) It only exists to demonstrate how cql-schema-versioning can be used.

To use this:

- Build cql_schema_versioning
- Start cassandra
- mvn clean jetty:run
- Browse to http://localhost:8080/cql-schema-versioning-example/

A page should show that says, 'App successfully started. Use cqlsh to dump your schema and see what it looks like.'

Review the console from the app as it starts, and you should see that two scripts were run. Restart the app and you should see that the scripts are not run a second time.
