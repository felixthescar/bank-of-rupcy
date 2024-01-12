namespace RazorPagesAccount.Models;

#pragma warning disable CS8618 // Non-nullable field must contain a non-null value when exiting constructor. Consider declaring as nullable.
public class Account {
    public long id {get; set;}
    public long clientId {get; set;}
    public long empId {get; set;}
    public long balance {get; set;}
}