using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using RazorPagesClient.Models;

namespace RazorPagesClient.Data
{
    public class RazorPagesClientContext : DbContext
    {
        public RazorPagesClientContext (DbContextOptions<RazorPagesClientContext> options)
            : base(options)
        {
        }

        public DbSet<RazorPagesClient.Models.Client> Client { get; set; } = default!;
    }
}
