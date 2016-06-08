//
//  SharedFilesArrayGetService.swift
//  PruebaInicial
//
//  Created by Estudiantes on 6/8/16.
//  Copyright © 2016 TCIceChat. All rights reserved.
//


import Restofire

class SharedFilesArrayGetService: Requestable {
    
    typealias Model = [[String: AnyObject]]
    var path: String = "/shared_files/"
    
    init(from: Int, to: Int){
        path+=String(from)+"/"+String(to)
    }
    
    
}