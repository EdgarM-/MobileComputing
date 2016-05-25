using ChatWindows.Model;
using System;
using System.Collections.Generic;
using System.Data.SQLite;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace ChatWindows.LocalStorage
{
    class DB
    {
        public string dbConnectionString { get; set; }

        public DB(string connection)
        {
            //= @"Data Source=database.db;Version=3;"                
            this.dbConnectionString = connection;
        }

        public void insertMessage(string from, string to, string message)
        {
            SQLiteConnection sqliteCon = new SQLiteConnection(this.dbConnectionString);
            try
            {
                sqliteCon.Open();
                string Query = "insert into Tmessage (fromCol,toCol,textCol) values ('" + from + "','" + to + "','" + message + "')";
                SQLiteCommand createCommand = new SQLiteCommand(Query, sqliteCon);
                createCommand.ExecuteNonQuery();
                sqliteCon.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        public List<Message> getUserMessages(string from, string to)
        {
            SQLiteConnection sqliteCon = new SQLiteConnection(dbConnectionString);
            List<Message> messages = new List<Message>();
            try
            {
                sqliteCon.Open();
                string Query = "select * from Tmessage where fromCol='" + from + "' and toCol='" + to + "' ";
                SQLiteCommand createCommand = new SQLiteCommand(Query, sqliteCon);
                createCommand.ExecuteNonQuery();

                SQLiteDataReader reader = createCommand.ExecuteReader();
                // reader.GetInt32(reader.GetOrdinal(3))
                while (reader.Read())
                {
                    if ((reader.GetString(3).Length) != 0)
                    {
                        Message msj = new Message(reader.GetInt32(1), reader.GetInt32(2), reader.GetString(3));
                        msj.DetallesMensajes(reader.GetInt32(0), reader.GetString(4));
                        messages.Add(msj);
                    }
                }
                MessageBox.Show("Selected");
                sqliteCon.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
            return messages;
        }

    }
}
