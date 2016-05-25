using ChatWindows.Model;
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
    /// Interaction logic for Contacts.xaml
    /// </summary>
    public partial class Contacts : Window
    {
        public Contacts(DataCtx ctx)
        {
            ctx.GetContacts();
            this.DataContext = ctx;
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Button f = (Button)sender;
            Messages ventanaChat = new Messages((DataCtx)this.DataContext, Int32.Parse(f.Name.ToString()),f.Content.ToString());
            ventanaChat.Show();
            this.Close();
        }
    }
}
