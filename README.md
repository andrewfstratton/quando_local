# Quando Local
Local support for Quando

Java based tools to allow Quando to locally access devices and some platform behaviour.

_Justification - WebUSB availability is still uncertain, at the time of writing, especially with Apple rejecting many of the useful browser extensions.  This may have the same effect as Flash blocking in iPhone and so WebUSB, etc., are being avoided._

Note: This will not be useful without Quando.

Local support offers:

* a local server running on port 8080 when the jarfile is executed
  * This server will also (currently) serve a local server on port 80 automatically.
* Access to micro:bit (and other usb devices) from the local machine
  * Install code for devices, currently:
    * microbit display, servo control, angle and gestures.
* **Experimental** access to keyboard control (will be disabled by default)
* **Experimental** access to mouse control (will be disabled by default)
* **NOT YET IMPLEMENTED** global capture of (local) keyboard (will be disabled by default)
  * N.B. This may never be enabled, since it can allow infitie keybord event looping

Prerequisite - Java (runtime) installed

# Running

Not yet executable from just opening the jarfile - permissions may be an issue.  Also, difficult to reset/stop.

To run:

1. open a command line
2. Navigate to the folder containing quando_local.jar
3. Run by entering: java -jar quando_local.jar

To stop:

1. Use Ctrl-C in the command line to stop the running program.
