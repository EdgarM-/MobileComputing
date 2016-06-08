//
//  MessagePOSTService.swift
//  PruebaInicial
//
//  Created by Estudiantes on 6/4/16.
//  Copyright © 2016 TCIceChat. All rights reserved.
//

import Restofire
import Alamofire

class MessagePOSTService {
    
    typealias Model = [[String: AnyObject]]
    var path: String = Restofire.defaultConfiguration.baseURL+"/messages/"
 

    func SendMessage(from: Int, to: Int, text: String) -> Void {
        let message : Dictionary<String,AnyObject> = Dictionary<String,AnyObject>(dictionaryLiteral: ("from",from), ("to", to),("text",text))//["from":from,"to" : to, "text":text]
        
        Alamofire.request(.POST, self.path, parameters: message, encoding: .JSON)
        debugPrint("Post",from, to)
    }
}


