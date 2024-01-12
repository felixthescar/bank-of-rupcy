using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;
using RazorPagesClient.Models;
using System.Net;
using System.Text;

namespace TragInElDotNet.Pages;

#pragma warning disable CS8601 // Possible null reference assignment.
#pragma warning disable CS1998 // Async method lacks 'await' operators and will run synchronously
#pragma warning disable SYSLIB0014 // Type or member is obsolete

[ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
[IgnoreAntiforgeryToken]
public class ClientModel : PageModel
{
    public string url = "http://localhost:8088/client";
    public List<Client> clients = new List<Client>();
    private readonly ILogger<ClientModel> _logger;

    public ClientModel(ILogger<ClientModel> logger)
    {
        _logger = logger;
    }

    public async Task OnGet()
    {
        using (var httpClient = new HttpClient())
        {
            using (HttpResponseMessage response = await httpClient.GetAsync(url + "/getAllClients"))
            {
                string apiResponse = await response.Content.ReadAsStringAsync();
                this.clients = JsonConvert.DeserializeObject<List<Client>>(apiResponse);
            }
        }
    }

    public async Task<IActionResult> OnPostAdd(string name, string surname, string npc, string phoneNr)
    {
        Client cl = new Client();
        cl.name = name;
        cl.surname = surname;
        cl.npc = npc;
        cl.phoneNr = phoneNr;

        HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url + "/createClient");
        httpWebRequest.Method = "POST";
        string json = JsonConvert.SerializeObject(cl);
        byte[] bytearray = Encoding.UTF8.GetBytes(json);
        httpWebRequest.ContentType = "application/json";
        httpWebRequest.ContentLength = bytearray.Length;
        Stream reqStream = httpWebRequest.GetRequestStream();
        reqStream.Write(bytearray, 0, bytearray.Length);
        WebResponse response = httpWebRequest.GetResponse();

        return Redirect("./Clients");
    }

    public async Task<IActionResult> OnPostModify(int id, string name, string surname, string npc, string phoneNr)
    {
        if (id != 0)
        {
            Client cl = new Client();
            cl.id = id;
            cl.name = name;
            cl.surname = surname;
            cl.npc = npc;
            cl.phoneNr = phoneNr;

            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url + "/modifyClient");
            httpWebRequest.Method = "POST";
            string json = JsonConvert.SerializeObject(cl);
            byte[] bytearray = Encoding.UTF8.GetBytes(json);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.ContentLength = bytearray.Length;
            Stream reqStream = httpWebRequest.GetRequestStream();
            reqStream.Write(bytearray, 0, bytearray.Length);
            WebResponse response = httpWebRequest.GetResponse();
        }
        return Redirect("./Clients");
    }

    public async Task<IActionResult> OnPostDelete(int id)
    {
        if (id != 0)
        {
            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url + "/deleteClient/" + id);
            httpWebRequest.Method = "DELETE";
            WebResponse response = httpWebRequest.GetResponse();
        }
        return Redirect("./Clients");
    }
}




