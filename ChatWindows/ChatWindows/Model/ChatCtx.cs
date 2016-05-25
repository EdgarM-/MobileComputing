using ChatWindows.LocalStorage;
using ChatWindows.Rest;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ChatWindows.Model
{
    class ChatCtx
    {
        public int from { get; set; }
        public int to { get; set; }
        public string nombre;
        public List<Message> mensajes;
        DataCtx ctx;
        DB BD;
        public ChatCtx(int from, int to, DataCtx ctx, string nombre)
        {
            this.from = from;
            this.to = to;
            this.ctx = ctx;
            this.BD =new DB("C:\Users\monitor\Desktop\database.db");
            this.nombre = nombre;
        }

        public void refreshMessages()
        {
            Get service = new Get("http://localhost:8191/rest/");
            List<Message> nMensajes = service.Messages(this.from, this.to);
            foreach (Message mensaje in nMensajes)
            {
                this.BD.insertMessage(mensaje.from.ToString(), mensaje.to.ToString(), mensaje.text);
                this.mensajes.Add(mensaje);
            }
        }



    }
}
