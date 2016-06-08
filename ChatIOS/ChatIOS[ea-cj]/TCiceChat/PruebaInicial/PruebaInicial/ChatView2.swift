//
//  ChatView2.swift
//  PruebaInicial
//
//  Created by Estudiantes on 6/4/16.
//  Copyright © 2016 TCIceChat. All rights reserved.
//

import UIKit
class ChatView2: UIViewController {

    
    @IBOutlet var TituloLbl: UILabel!
    @IBOutlet var TituloItemBar: UINavigationItem!
    //@IBOutlet var titulo: UINavigationItem!
    //@IBOutlet var NavTitle: UINavigationItem!
    @IBOutlet var textoEntrante: UITextField!
    @IBOutlet var tableView: UITableView!
    var userDestino:Contact = Contact(id: 1,name: "a",userName: "b")
    //var userRemitente: Contact = Contact(id: 1, name: "a", userName: "b")
    var userRemitente : Int = 0
    var msgs:[Message] = [Message]()
    
    var cManager : ChatManager = ChatManager()
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        var identifier:String = "Message2"
        var cell:CustomMessageTableViewCell
        let valor = indexPath.row
        let fromMsg = cManager.getFromMsg(valor)
        print(cManager.getMsg(valor).idMessage,cManager.getMsg(valor).from, cManager.getMsg(valor).to )
        if fromMsg == userDestino.id {
            identifier = "Message1"
            cell = tableView.dequeueReusableCellWithIdentifier(identifier) as! CustomMessageTableViewCell
            let msg = cManager.getMsg(valor)
            cell.lblM3?.text = String(msg.text)
        }else{
            cell = tableView.dequeueReusableCellWithIdentifier(identifier) as! CustomMessageTableViewCell
            let msg = cManager.getMsg(valor)
            cell.lblM4?.text = String(msg.text)
        }
        tableView.scrollToRowAtIndexPath(indexPath, atScrollPosition: UITableViewScrollPosition.Top, animated: false)

        return cell
    }
    func tableView(tableView: UITableView,numberOfRowsInSection section: Int) -> Int{
        return cManager.count();
        
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.textoEntrante.keyboardType = UIKeyboardType.Default
        self.TituloLbl.text? = self.userDestino.name
        self.cManager.start(self.userRemitente, to: self.userDestino.id)
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
    
    @IBAction func Enviar(sender: AnyObject) {
        let texto:String = textoEntrante.text!
        cManager.addMessge(userRemitente, to: userDestino.id, text: texto)
        let postSer: MessagePOSTService = MessagePOSTService()
        postSer.SendMessage(userRemitente, to:userDestino.id,text:texto)
        textoEntrante.clearButtonMode = .WhileEditing
        textoEntrante.text = nil
        
        self.refresh()
        //tableView.reloadData()
    }
    
        
    func refresh() -> Void {
        self.cManager.start(self.userRemitente, to: self.userDestino.id)
        let seconds = 0.5
        let delay = seconds*Double(NSEC_PER_SEC)
        let dispatchtime = dispatch_time(DISPATCH_TIME_NOW,Int64(delay))
        dispatch_after(dispatchtime, dispatch_get_main_queue(), {
            self.viewWillAppear(true)
            
        })
    }
    
    
}
