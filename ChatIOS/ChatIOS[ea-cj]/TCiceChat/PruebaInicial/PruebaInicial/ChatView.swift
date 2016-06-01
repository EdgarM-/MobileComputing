//
//  ChatView.swift
//  PruebaInicial
//
//  Created by Estudiantes on 5/31/16.
//  Copyright © 2016 TCIceChat. All rights reserved.
//

import UIKit

class ChatView: UIViewController, UITableViewDataSource{
    
    @IBOutlet var nombreChatTop: UINavigationItem!
    
    @IBOutlet var tableView: UITableView!
    var NombreEntrante:Contact = Contact(id: 1,name: "a",userName: "b")
    
    var cManager : ChatManager = ChatManager()
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell:CustomTableViewCell = tableView.dequeueReusableCellWithIdentifier("Custom01") as! CustomTableViewCell
        
        return cell
    }
    
    func tableView(tableView: UITableView,numberOfRowsInSection section: Int) -> Int{
        return cManager.contactCount();
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        nombreChatTop.title = NombreEntrante.name
        
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    override func viewWillAppear(animated: Bool) {
        tableView.reloadData()
    }
}
