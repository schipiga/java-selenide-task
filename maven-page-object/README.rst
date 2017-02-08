==================
How to launch test
==================

Inside project folder, please execute next command in terminal::

    $ mvn clean test

======================
Generate allure report
======================

Execute next commands in terminal::

    $ mvn site
    $ mvn jetty:run

If you have timout exception with jetty, try next command::

    $ mvn jetty:run -Dorg.eclipse.jetty.annotations.maxWait=120  # set a higher value if needed

=============
Video capture
=============

Recorded video file of launched test is located inside folder ``video`` of project.

If you want to disable video capture, please use command::

    $ mvn test -Dvideo.disable=true
