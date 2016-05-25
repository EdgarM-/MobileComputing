using ChatWindows.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace ChatWindows.Rest
{
    class Post
    {
        public string url;
        public Post(string url)
        {
            this.url = url;
        }
        public void message(int from, int to, string content)
        {
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(this.url);
            Message mensaje = new Message(from, to , content);
            client.PostAsJsonAsync("messages/", mensaje).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode());
        }
    }
}
