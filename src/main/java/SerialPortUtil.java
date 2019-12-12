import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

public class SerialPortUtil {

    public static void listPorts() {
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier portIdentifier = (CommPortIdentifier) portEnum.nextElement();
            System.out.println(portIdentifier.getName());
        }
    }

    static boolean checkSerialConnection() {
        boolean serialPortConnected = false;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier portIdentifier = (CommPortIdentifier) portEnum.nextElement();
            if (portIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                serialPortConnected = true;
            }
        }
        return serialPortConnected;
    }
}