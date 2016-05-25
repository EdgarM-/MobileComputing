using ChatWindows.Rest;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ChatWindows.Model
{
    public class DataCtx
    {
        public int user { get; set; }
        public List<Contact> contacts { get; set; }
        public void setId(int id)
        {
            this.user = id;
        }
        public void GetContacts()
        {
            Get contactos = new Get("http://localhost:8191/rest/");
            this.contacts = contactos.Contacts(this.user);
            Console.WriteLine("Contacto " + this.user.ToString());
        }

    }
}
