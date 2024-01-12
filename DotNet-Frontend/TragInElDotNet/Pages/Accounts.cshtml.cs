using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;
using RazorPagesAccount.Models;
using System.Net;
using System.Text;

namespace TragInElDotNet.Pages;

#pragma warning disable CS8601 // Possible null reference assignment.
#pragma warning disable CS1998 // Async method lacks 'await' operators and will run synchronously
#pragma warning disable SYSLIB0014 // Type or member is obsolete

[ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
[IgnoreAntiforgeryToken]
public class AccountModel : PageModel
{
    public string url = "http://localhost:8088/account";
    public List<Account> accounts = new List<Account>();
    private readonly ILogger<AccountModel> _logger;

    public AccountModel(ILogger<AccountModel> logger)
    {
        _logger = logger;
    }

    public async Task OnGet()
    {
        using (var httpClient = new HttpClient())
        {
            using (HttpResponseMessage response = await httpClient.GetAsync(url + "/getAllAccounts"))
            {
                string apiResponse = await response.Content.ReadAsStringAsync();
                Console.WriteLine(apiResponse);
                this.accounts = JsonConvert.DeserializeObject<List<Account>>(apiResponse);
            }
        }
    }

    public async Task<IActionResult> OnPostAdd(long balance, long angajat_id, long client_id)
    {
        Account ac = new Account();
        ac.balance = balance;
        ac.clientId = angajat_id;
        ac.empId = client_id;

        HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url + "/createAccount");
        httpWebRequest.Method = "POST";
        string json = JsonConvert.SerializeObject(ac);
        byte[] bytearray = Encoding.UTF8.GetBytes(json);
        httpWebRequest.ContentType = "application/json";
        httpWebRequest.ContentLength = bytearray.Length;
        Stream reqStream = httpWebRequest.GetRequestStream();
        reqStream.Write(bytearray, 0, bytearray.Length);
        WebResponse response = httpWebRequest.GetResponse();

        return Redirect("./Account");
    }

    public async Task<IActionResult> OnPostModify(int id, long balance, long angajat_id, long client_id)
    {
        if (id != 0)
        {
            Account ac = new Account();
            ac.id = id;
            ac.balance = balance;
            ac.clientId = angajat_id;
            ac.empId = client_id;

            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url + "/modifyAccount");
            httpWebRequest.Method = "POST";
            string json = JsonConvert.SerializeObject(ac);
            byte[] bytearray = Encoding.UTF8.GetBytes(json);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.ContentLength = bytearray.Length;
            Stream reqStream = httpWebRequest.GetRequestStream();
            reqStream.Write(bytearray, 0, bytearray.Length);
            WebResponse response = httpWebRequest.GetResponse();
        }
        return Redirect("./Account");
    }

    public async Task<IActionResult> OnPostDelete(int id)
    {
        if (id != 0)
        {
            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url + "/deleteAccount/" + id);
            httpWebRequest.Method = "DELETE";
            WebResponse response = httpWebRequest.GetResponse();
        }
        return Redirect("./Account");
    }
}