using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;
using WebApp.Models;

namespace WebApp.Controllers
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    [RoutePrefix("")]
    public class AdminController : ApiController
    {
        QuanLyBanHangDataContext db = new QuanLyBanHangDataContext();

        [HttpPost]
        [ActionName("DangNhap")]
        public IHttpActionResult dangNhap([FromBody] DN admin)
        {
            try
            {
                Admin ad = db.Admins.FirstOrDefault(e => e.user.Equals(admin.user) && e.pass.Equals(admin.pass));
                if (ad == null)
                {
                    return StatusCode(HttpStatusCode.NoContent);
                }
                return Ok(ad.token);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
    }
}
