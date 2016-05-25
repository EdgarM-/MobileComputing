using ChatWindows.Model;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Linq;
using System.Threading.Tasks;

namespace ChatWindows.Rest
{
    public class Get
    {
        public string url;
        public Get(string url)
        {
            this.url = url;
        }

        public List<Contact> Contacts(int user)
        {
            List<Contact> contactos = new List<Contact>();
            HttpClient client = new HttpClient();            
            client.BaseAddress= new Uri(this.url);
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage request = client.GetAsync("contacts/" + user.ToString()).Result;
            Console.WriteLine(request.ToString());
            if (request.IsSuccessStatusCode)
            {
                contactos = request.Content.ReadAsAsync<IEnumerable<Contact>>().Result.ToList();
            }

            return contactos;
        }

        public List<Message> Messages(int from, int to)
        {
            List<Message> mensajes = new List<Message>();
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(this.url);
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage request = client.GetAsync("messages/" + from.ToString()+"/"+to.ToString()).Result;
            if (request.IsSuccessStatusCode)
            {
                mensajes = request.Content.ReadAsAsync<IEnumerable<Message>>().Result.ToList();
            }

            return mensajes;
        }

        public List<File> Files(int from, int to)
        {
            List<File> archivos = new List<File>();
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(this.url);
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage request = client.GetAsync("shared_files/" + from.ToString() + "/" + to.ToString()).Result;
            if (request.IsSuccessStatusCode)
            {
                archivos = request.Content.ReadAsAsync<IEnumerable<File>>().Result.ToList();
            }

            return archivos;
        }
    }
}
