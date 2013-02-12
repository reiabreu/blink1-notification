blink1-notification
=================

Weather notification service using blink1 tool (see http://thingm.com/products/blink-1.html)

This application is very simple and changes the RGB colour according with the current conditions in a city.
You will need a http://www.wunderground.com/ API key.

This application uses maven. You have to install in your local repository the latest blink1.jar which you can get at:
http://thingm.com/blink1/downloads/blink1-java-processing-lib.zip

After this: maven clean install assembly:assembly. This will create a .tar.gz containing the entire application.

