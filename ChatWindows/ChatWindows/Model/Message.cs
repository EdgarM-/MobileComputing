using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ChatWindows.Model
{
    public class Message
    {
        public int id { get; set; }
        public int from { get; set; }
        public int to { get; set; }
        public String text { get; set; }
        public String date { get; set; }

        public Message(int from, int to, String text)
        {
            this.from = from;
            this.to = to;
            this.text = text;
            
        }

        public void DetallesMensajes(int id, String date)
        {
            this.id = id;
            this.date = date;
        }
    }
}
