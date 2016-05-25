using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ChatWindows.Model
{
    public class File
    {
        public int id { get; set; }
        public int from { get; set; }
        public int to { get; set; }
        public String date { get; set; }
        public String name { get; set; }
        public String contentType { get; set; }

        public File(int id, int from, int to, String name, String date, String contentType)
        {
            this.id = id;
            this.from = from;
            this.to = to;
            this.name = name;
            this.date = date;
            this.contentType = contentType;
        }
    }
}
