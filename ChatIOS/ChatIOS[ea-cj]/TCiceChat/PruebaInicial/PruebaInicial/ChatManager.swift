//
//  ChatManager.swift
//  PruebaInicial
//
//  Created by Estudiantes on 5/31/1ights reserved.
//

import SQLite
import UIKit

class ChatManager {
    
    static var db:MessageStorage = MessageStorage()
    var MessagesG = [Message]()

    init(){
        
    }
    
    func addMessge(from:Int, to:Int, text:String) {
        ChatManager.db.addMessge(from, to: to, text: text)
    }
    
    //Trae los mensajes de la base de datos
    /*func getMessages(from:Int, to:Int) -> Void{
        MessagesG = ChatManager.db.getMessages(from, to: to)
        MessagesG.appendContentsOf(ChatManager.db.getMessages(to, to: from))
    }*/
    
    
    func getMessages(from : Int, to: Int) -> Void{
        var value: [[String: AnyObject]]!
        MessagesG = ChatManager.db.getMessages(from, to: to)
        MessagesG.appendContentsOf(ChatManager.db.getMessages(to, to: from))
        MessageArrayGETService(from: to,to: from).executeTask() {
            if let v = $0.result.value {
                value = v
                for v in value {
                    self.MessagesG.append(Message(idMessage: v["id"] as! Int,from: v["from"] as! Int,to: v["to"] as! Int, text: v["text"] as! String,date: v["date"] as! String))
                    //ChatManager.db.addMessge(v["from"] as! Int,to: v["to"] as! Int, text: v["text"] as! String)
                    print("get",v)
                }
            }
            
        }
        
        MessagesG.sortInPlace({(Message1:Message,Message2:Message) -> Bool in return Message1.idMessage < Message2.idMessage})
        
    }
   
    func start(from:Int, to:Int) -> Void {
        self.MessagesG.removeAll()
        self.getMessages(from, to: to)
    }
  
    func getMsg(id:Int) -> Message{
        //debugPrint(id,MessagesG)
        return MessagesG[id];
    }
    func getFromMsg(id : Int) -> Int {
        let idFrom = MessagesG[id].from
        return idFrom
    }
        
    func count() -> Int {
        return MessagesG.count;
    }
    
    
}