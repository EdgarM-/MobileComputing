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
        public string nombre { get; set; }
        public List<Message> mensajes { get; set; }
        public string mensaje { get; set; }
        public DataCtx ctx { get; set; }
        public DB BD { get; set; }
        public ChatCtx(int from, int to, DataCtx ctx, String nombre)
        {
            this.from = from;
            this.to = to;
            this.ctx = ctx;
            string dbcon = @"Data Source=C:/Users/monitor/Desktop/database.db;Version=3;";
            this.BD =new DB(dbcon);
            this.nombre = nombre;
            this.mensajes = new List<Message>();
            this.LoadMessages();
        }
        public void LoadMessages()
        {
            this.refreshMessages();
            mensajes.AddRange(this.BD.getUserMessages(this.from.ToString(), this.to.ToString()));
            mensajes.AddRange(this.BD.getUserMessages(this.to.ToString(), this.from.ToString()));
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
