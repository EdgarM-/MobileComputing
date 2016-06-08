//
//  sContactView.swift
//  PruebaInicial
//
//  Created by Estudiantes on 5/31/16.
//  Copyright © 2016 TCIceChat. All rights reserved.
///Users/estudiantes/Documents/Native Instruments/FM8/Moviles[ea-cj]_3/TCiceChat/PruebaInicial/PruebaInicial.xcodeproj

import UIKit

class sContactViewController: UIViewController, UITableViewDataSource {
    
    //@IBOutlet weak var lblID: UILabel!
    @IBOutlet var tableView: UITableView!
    var idEntrar:Int = 1
    var cManager = ContactManager ()
    var nombreUserDestino:Contact = Contact(id: 1,name: "a",userName: "b")
    var nombreUserRemitente:Contact = Contact(id: 1,name: "a",userName: "b")
    var userRemitente : Int = 1
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell:CustomTableViewCell = tableView.dequeueReusableCellWithIdentifier("Custom01") as! CustomTableViewCell
        let contact = cManager.getContactAt(indexPath.row)
        cell.nameLbl.text = contact?.name
        cell.userNameLbl.text = contact?.userName
        
        return cell
    }
    
    func tableView(tableView: UITableView,numberOfRowsInSection section: Int) -> Int{
        return cManager.contactCount();
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        cManager.getContacts(idEntrar)
        let nameRemitente:String = "Carlos"
        let userNameRemitente:String = "Jaramillo"
        //cManager.getContactAt(idEntrar)?.userName
        nombreUserRemitente = Contact(id: idEntrar, name: nameRemitente, userName: userNameRemitente)
        //lblID.text? = "usuario: \(idEntrar)"
        
        let seconds = 1.0
        let delay = seconds*Double(NSEC_PER_SEC)
        let dispatchtime = dispatch_time(DISPATCH_TIME_NOW,Int64(delay))
        dispatch_after(dispatchtime, dispatch_get_main_queue(), {
            self.viewWillAppear(true)
        
        })
    }
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if(segue.identifier == "Tab1Conversar"){
            let tabBarController = segue.destinationViewController as! TabBarController1;
            let destinationViewController = tabBarController.viewControllers![0] as! ChatView2
            nombreUserDestino = cManager.getContactAt((tableView.indexPathForSelectedRow?.row)!)!
            userRemitente = idEntrar
            destinationViewController.userDestino = nombreUserDestino
            destinationViewController.userRemitente = nombreUserRemitente.id
            print("Id from",nombreUserDestino.id,"Id to",nombreUserRemitente.id)
        }
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    override func viewWillAppear(animated: Bool) {
        tableView.reloadData()
    }
    

}
