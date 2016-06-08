//
//  FileGetService.swift
//  PruebaInicial
//
//  Created by Estudiantes on 6/8/16.
//  Copyright © 2016 TCIceChat. All rights reserved.
//


import Alamofire
import Restofire

class FileGetService {
    var path: String = Restofire.defaultConfiguration.baseURL+"/files/"

    func getFile(id: Int) -> Void {
        Alamofire.download(.GET, path+String(id)) { temporaryURL, response in
            let fileManager = NSFileManager.defaultManager()
            let directoryURL = fileManager.URLsForDirectory(.DocumentDirectory, inDomains: .UserDomainMask)[0]
            let pathComponent = response.suggestedFilename
            print("PathDownload: ",directoryURL.URLByAppendingPathComponent(pathComponent!))
            return directoryURL.URLByAppendingPathComponent(pathComponent!)
        }
    }
    func getFileWithProgress(id : Int) -> Void {
        let destination = Alamofire.Request.suggestedDownloadDestination()
        Alamofire.download(.GET, path+String(id), destination: destination)
        //Alamofire.download(.GET, path+String(id), destination: destination)
            .progress { bytesRead, totalBytesRead, totalBytesExpectedToRead in
                print(totalBytesRead)
                
                // This closure is NOT called on the main queue for performance
                // reasons. To update your ui, dispatch to the main queue.
                dispatch_async(dispatch_get_main_queue()) {
                    print("Total bytes read on main queue: \(totalBytesRead)")
                }
            }
            .response { _, _, _, error in
                if let error = error {
                    print("Failed with error: \(error)")
                } else {
                    print("Downloaded file successfully")
                }
        }
    }
}
