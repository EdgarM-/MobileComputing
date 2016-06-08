//
//  FileManager.swift
//  PruebaInicial
//
//  Created by Estudiantes on 6/8/16.
//  Copyright © 2016 TCIceChat. All rights reserved.
//[{"id":1,"name":"Plus-32.png","contentType":"image/png","from":1,"to":2,"date":"2016-06-08 19:19:16"}]

import UIKit
class FileManager{
    var fileShared = [File]()
    var downloader = FileGetService()
    func getFiles(from: Int, to: Int) -> Void{
        var value: [[String: AnyObject]]!
        SharedFilesArrayGetService(from: to,to: from).executeTask() {
            
            if let v = $0.result.value {
                value = v
                var tempFiles = [File]()
                for v in value {
                    
                    tempFiles.append(File(idFile : v["id"] as! Int,from : v["from"] as! Int, to : v["to"] as! Int, name: v["name"] as! String, contentType:v["contentType"] as! String, date:v["date"] as! String))
                    //ChatManager.db.addMessge(v["from"] as! Int,to: v["to"] as! Int, text: v["text"] as! String)
                    print("get",v)
                }
                self.fileShared = tempFiles
            }
            
        }
    
    }
    
    func getFileAt(pos : Int) -> File? {
        //debugPrint(contactList[1])
        if(pos >= 0 && pos < fileShared.count){
            return self.fileShared[pos]
        }
        return nil;
    }
    
    func fileCount() -> Int {
        return self.fileShared.count
    }
    
    func downloadFile(Id : Int) -> Void {
        //downloader.getFile(Id)
        downloader.getFileWithProgress(Id)
    }
}
