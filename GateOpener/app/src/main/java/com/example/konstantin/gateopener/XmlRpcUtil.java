package com.example.konstantin.gateopener;// aXMLRPC_v1.7.2.jar

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import de.timroes.axmlrpc.XMLRPCClient;
import de.timroes.axmlrpc.XMLRPCException;

/**
 * Created by Konstantin on 05.09.2015.
 */

public class XmlRpcUtil {
    final String UP = "up";
    final String DOWN = "down";

    static XmlRpcUtil xml = new XmlRpcUtil();
    XMLRPCClient client;
    String url = "http://192.168.0.100:44444/RPC2";

    //remote methods list                       // *methods params              //returns:
    String move = "move";       //void
    String stopAll = "stop_all";        //void

    public void moveIt(String direction) throws MalformedURLException, XMLRPCException {
        client = new XMLRPCClient(new URL(url));
        client.setTimeout(3);
        try{
            client.call(move, direction);
        }catch (XMLRPCException e){
            System.out.println("here exception");
        }
    }

    public void stopAllMoves() throws MalformedURLException, XMLRPCException {
        client = new XMLRPCClient(new URL(url));
        client.setTimeout(3);
        try {
            client.call(stopAll);
        }catch (XMLRPCException e){
            System.out.println("here exception");
        }
    }

    public static void main(String[] args) throws MalformedURLException, XMLRPCException {
        XmlRpcUtil test = new XmlRpcUtil();
        test.moveIt(test.DOWN);
        test.moveIt(test.UP);
//        test.stopAllMoves();
    }
}
