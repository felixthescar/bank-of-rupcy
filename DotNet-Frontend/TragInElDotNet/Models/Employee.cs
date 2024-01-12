namespace RazorPagesEmployee.Models;

#pragma warning disable CS8618 // Non-nullable field must contain a non-null value when exiting constructor. Consider declaring as nullable.
public class Employee {
    public long id {get; set;}
    public string name {get; set;}
    public string surname {get; set;}
    public string npc {get; set;}
    public string phoneNr {get; set;}
    public string salary {get; set;}
}