//
//  ChatManager.swift
//  PruebaInicial
//
//  Created by Estudiantes on 5/31/16.
//  Copyright © 2016 TCIceChat. All rights reserved.
//


import UIKit

class ChatManager {
    var chatList: Array<Message> = Array<Message>();
    
    func getMessages(from : Int, to: Int) -> Void{
        let lista : Array<Message> = [Message(idMessage : 1,from : 1, to : 2, text: "TU cucha es hombre", date:"16/12/2006"),Message(idMessage : 2,from : 2, to : 1, text: "TU hermana es hombre", date:"16/12/2006")]
        chatList = lista
    }
    
    func getMessageAt(pos : Int) -> Message? {
        if(pos >= 0 && pos < self.contactCount()){
            return self.chatList[pos]
        }
        return nil;
    }
    
    func contactCount() -> Int {
        return self.chatList.count
    }
}
