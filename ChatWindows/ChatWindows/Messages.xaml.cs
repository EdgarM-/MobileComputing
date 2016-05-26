using ChatWindows.LocalStorage;
using ChatWindows.Model;
using ChatWindows.Rest;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace ChatWindows
{
    /// <summary>
    /// Interaction logic for Messages.xaml
    /// </summary>
    public partial class Messages : Window
    {
        public Messages(DataCtx ctx, int id, string name)
        {
            this.DataContext = new ChatCtx(id, ctx.user, ctx, name);

            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Post sendM = new Post("http://localhost:8191/rest/");
            ChatCtx ctx = (ChatCtx)this.DataContext;
            sendM.Message(ctx.from, ctx.to, ctx.mensaje);
            string dbcon = @"Data Source=C:/Users/monitor/Desktop/database.db;Version=3;";
            DB Store = new DB(dbcon);
            Store.insertMessage(ctx.from.ToString(), ctx.to.ToString(), ctx.mensaje);
            ctx.mensaje = "";
            this.UpdateLayout();

        }
    }
}
