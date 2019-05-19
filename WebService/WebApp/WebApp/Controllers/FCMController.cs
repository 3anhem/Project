using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Web.Http;
using System.Web.Http.Cors;
using System.Web.Script.Serialization;
using WebApp.Models;

namespace WebApp.Controllers
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    [RoutePrefix("")]
    public class FCMController : ApiController
    {
        private QuanLyBanHangDataContext db = new QuanLyBanHangDataContext();

        [HttpGet]
        [ActionName("kiemTra")]
        public IHttpActionResult kiemtra(string device, string token)
        {
            try
            {
                FCM fcm = db.FCMs.FirstOrDefault(e => e.device.Equals(device) && e.token.Equals(token));

                if (fcm == null)
                {
                    return StatusCode(HttpStatusCode.NotFound);
                }
                return Ok(fcm);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPost]
        [ActionName("TaoMoi")]
        public IHttpActionResult taoMoi(string device, string token)
        {
            try
            {
                FCM fcm = db.FCMs.FirstOrDefault(e => e.device.Equals(device) && e.token.Equals(token));

                if (fcm == null)
                {
                    FCM tam = new FCM();
                    tam.device = device;
                    tam.token = token;

                    db.FCMs.InsertOnSubmit(tam);
                    db.SubmitChanges();

                    return Ok(tam);
                }
                return Ok(fcm);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        //
        [HttpGet]
        [ActionName("DangXuat")]
        public IHttpActionResult DangXuatFCM(int id)
        {
            try
            {
                FCM fcm = db.FCMs.FirstOrDefault(e => e.khach_hang == id);
                if (fcm == null)
                {
                    return StatusCode(HttpStatusCode.NotFound);
                }
                fcm.khach_hang = 0;
                db.SubmitChanges();
                return Ok();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpGet]
        [ActionName("DanhSach")]
        public IHttpActionResult danhSach()
        {
            try
            {
                List<FCM> list = db.FCMs.ToList();
                if (list.Count == 0)
                {
                    return StatusCode(HttpStatusCode.NotFound);
                }

                return Ok(list);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        [HttpDelete]
        [ActionName("Xoa")]
        public IHttpActionResult Xoa(int id)
        {
            try
            {
                FCM fcm = db.FCMs.FirstOrDefault(e => e.id == id);
                if (fcm == null)
                {
                    return StatusCode(HttpStatusCode.NoContent);
                }
                db.FCMs.DeleteOnSubmit(fcm);
                db.SubmitChanges();
                return Ok();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }  
    }
}
