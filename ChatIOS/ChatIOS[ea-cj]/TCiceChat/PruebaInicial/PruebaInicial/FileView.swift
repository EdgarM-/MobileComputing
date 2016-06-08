//
//  FileView.swift
//  PruebaInicial
//
//  Created by Estudiantes on 6/8/16.
//  Copyright © 2016 TCIceChat. All rights reserved.
//

import UIKit

class FileView: UIViewController,UITableViewDelegate, UITableViewDataSource {
    
    var userDestino:Contact = Contact(id: 1,name: "a",userName: "b")
    var userRemitente : Int = 0
    @IBOutlet var titulo: UILabel!
    @IBOutlet var tableView: UITableView!
    var fManager:FileManager = FileManager()
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let identifier = "Custom2"
        let cell:CustomFileTableViewCell = tableView.dequeueReusableCellWithIdentifier(identifier) as! CustomFileTableViewCell
        let file = fManager.getFileAt(indexPath.row)
        cell.filename.text = file?.name
        
        tableView.scrollToRowAtIndexPath(indexPath, atScrollPosition: UITableViewScrollPosition.Top, animated: true)
    
    return cell
    }
    func tableView(tableView: UITableView,numberOfRowsInSection section: Int) -> Int{
        return fManager.fileCount()
        
    }
    @IBAction func downloadFile(sender: AnyObject) {
        print(tableView.indexPathForSelectedRow?.row)
        let downloadFile = fManager.getFileAt((tableView.indexPathForSelectedRow?.row)!)
        fManager.downloadFile((downloadFile?.idFile)!)
        
    }
    
    @IBOutlet var fileDownload: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.titulo.text? = userDestino.name
        self.fManager.getFiles(self.userRemitente, to: self.userDestino.id)
        self.refresh()
        //self.cManager.start(self.userRemitente, to: self.userDestino.id)
        /*let seconds = 0.5
         let delay = seconds*Double(NSEC_PER_SEC)
         let dispatchtime = dispatch_time(DISPATCH_TIME_NOW,Int64(delay))
         dispatch_after(dispatchtime, dispatch_get_main_queue(), {
         self.viewWillAppear(true)
         
         })*/
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    override func viewWillAppear(animated: Bool) {
        tableView.reloadData()
    }
    
       
    
    func refresh() -> Void {
        self.fManager.getFiles(self.userRemitente, to: self.userDestino.id)
        let seconds = 0.5
        let delay = seconds*Double(NSEC_PER_SEC)
        let dispatchtime = dispatch_time(DISPATCH_TIME_NOW,Int64(delay))
        dispatch_after(dispatchtime, dispatch_get_main_queue(), {
            self.viewWillAppear(true)
            
        })
    }

}
