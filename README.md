# Quando Local
Local support for Quando

Java based tools to allow Quando to locally access devices and some platform behaviour.
WebUSB availability is still uncertain, at the time of writing, especially with Apple rejecting many of the useful browser extensions.  This may have the same effect as Flash blocking in iPhone and so WebUSB, etc., are being avoided.

Note: This will not be useful without Quando.

Local support will offer:
* a local server running on port 8080 when the jarfile is executed
* Access to micro:bit and other usb devices from the local machine
* Install code for devices
* **Experimental** access to mouse and keyboard control (will be disabled by default)
* **Experimental** global capture of keyboard (will be disabled by default)
