==================
How to launch test
==================

Inside project folder, please execute next command in terminal::

    $ gradle clean test

======================
Generate allure report
======================

First of all, please install `allure-cli <http://wiki.qatools.ru/display/AL/Allure+Commandline>`_.

Then execute next commands in terminal::

    $ allure generate target/allure-results -o build/reports/allure
    $ allure report open -o build/reports/allure

=============
Video capture
=============

Recorded video file of launched test is located inside folder ``video`` of project.

If you want to disable video capture, please use command::

    $ gradle -Dvideo.disable=true test
