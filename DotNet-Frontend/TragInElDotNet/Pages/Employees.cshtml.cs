using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Newtonsoft.Json;
using RazorPagesEmployee.Models;
using System.Net;
using System.Text;

namespace TragInElDotNet.Pages;

#pragma warning disable CS8601 // Possible null reference assignment.
#pragma warning disable CS1998 // Async method lacks 'await' operators and will run synchronously
#pragma warning disable SYSLIB0014 // Type or member is obsolete

[ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
[IgnoreAntiforgeryToken]
public class EmployeeModel : PageModel
{
    public string url = "http://172.17.0.1:8088/employee";
    public List<Employee> employees = new List<Employee>();
    private readonly ILogger<EmployeeModel> _logger;

    public EmployeeModel(ILogger<EmployeeModel> logger)
    {
        _logger = logger;
    }

    public async Task OnGet()
    {
        using (var httpClient = new HttpClient())
        {
            using (HttpResponseMessage response = await httpClient.GetAsync(url + "/getAllEmployees"))
            {
                string apiResponse = await response.Content.ReadAsStringAsync();
                this.employees = JsonConvert.DeserializeObject<List<Employee>>(apiResponse);
            }
        }
    }

    public async Task<IActionResult> OnPostAdd(string name, string surname, string npc, string phoneNr, string salary)
    {
        Employee em = new Employee();
        em.name = name;
        em.surname = surname;
        em.npc = npc;
        em.phoneNr = phoneNr;
        em.salary = salary;

        HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url + "/createEmployee");
        httpWebRequest.Method = "POST";
        string json = JsonConvert.SerializeObject(em);
        byte[] bytearray = Encoding.UTF8.GetBytes(json);
        httpWebRequest.ContentType = "application/json";
        httpWebRequest.ContentLength = bytearray.Length;
        Stream reqStream = httpWebRequest.GetRequestStream();
        reqStream.Write(bytearray, 0, bytearray.Length);
        WebResponse response = httpWebRequest.GetResponse();

        return Redirect("./Employees");
    }

    public async Task<IActionResult> OnPostModify(int id, string name, string surname, string npc, string phoneNr, string salary)
    {
        if (id != 0)
        {
            Employee em = new Employee();
            em.id = id;
            em.name = name;
            em.surname = surname;
            em.npc = npc;
            em.phoneNr = phoneNr;
            em.salary = salary;


            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url + "/modifyEmployee");
            httpWebRequest.Method = "POST";
            string json = JsonConvert.SerializeObject(em);
            byte[] bytearray = Encoding.UTF8.GetBytes(json);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.ContentLength = bytearray.Length;
            Stream reqStream = httpWebRequest.GetRequestStream();
            reqStream.Write(bytearray, 0, bytearray.Length);
            WebResponse response = httpWebRequest.GetResponse();
        }
        return Redirect("./Employees");
    }

    public async Task<IActionResult> OnPostDelete(int id)
    {
        if (id != 0)
        {
            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url + "/deleteEmployee/" + id);
            httpWebRequest.Method = "DELETE";
            WebResponse response = httpWebRequest.GetResponse();
        }
        return Redirect("./Employees");
    }
}
