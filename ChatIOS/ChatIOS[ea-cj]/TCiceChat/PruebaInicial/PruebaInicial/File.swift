//
//  File.swift
//  PruebaInicial
//
//  Created by Estudiantes on 6/8/16.
//  Copyright © 2016 TCIceChat. All rights reserved.
//

import UIKit

class File {
    var idFile : Int;
    var from : Int;
    var to : Int;
    var name : String;
    var contentType: String;
    var date : String;
    
    init (idFile : Int ,from : Int, to : Int, name: String, contentType:String, date:String){
        self.from = from;
        self.to = to;
        self.name = name;
        self.date = date;
        self.idFile = idFile;
        self.contentType = contentType;
    }
    
}
