using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Text;
using System.Web;
using System.Security.Cryptography;

namespace WebApp.Models
{
    public class guiEmail
    {
        QuanLyBanHangDataContext db = new QuanLyBanHangDataContext();
        public void SendGmail(string toEmail, string toName, string mailBody, string mailSubject)
        {
            string fromEmail = "vtnshop@vtnstore.azurewebsites.net";
            string password = "vtnshop@@123";          
            string fromName = "VTNshop";
            var fromAddress = new MailAddress(fromEmail, fromName);
            var toAddress = new MailAddress(toEmail, toName);

            var smtp = new SmtpClient
            {
                Host = "smtp.gmail.com",
                Port = 587,
                EnableSsl = true,
                DeliveryMethod = SmtpDeliveryMethod.Network,
                UseDefaultCredentials = false,
                Credentials = new NetworkCredential(fromEmail, password),
                Timeout = 20000,

            };

            var message = new MailMessage(fromAddress, toAddress);
            message.IsBodyHtml = true;
            message.Subject = mailSubject;
            message.Body = mailBody;
            smtp.Send(message);
        }
        public string maimailBody(string email, string matKhau)
        {
            string chuoi = Encrypt(matKhau);

            string link = "http://vtnstore.azurewebsites.net/api/KhachHang/xacnham?taiKhoan=" + email + "&&matKhau=" + chuoi;
            string tam = "<head> <title>Udacity_email</title> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> <meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"> <!--[if !mso]><!-- --><meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"><!--<![endif]--> <!--[if !mso]><!-- --><link href=\"https://fonts.googleapis.com/css?family=Open+Sans:600,400,300\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]--> <style type=\"text/css\">html, body { background-color:#fafbfc; } img { display:block; } .ReadMsgBody {width: 100%; } .ExternalClass {width: 100%; } * { -webkit-text-size-adjust: none; } .whiteLinks a:link, .whiteLinks a:visited { color:#ffffff!important;} .appleLinksGrey a { color:#b7bdc1!important; text-decoration:none!important; } table {border-collapse:collapse;} .preheader{ font-size: 1px; line-height:1px; display: none!important; mso-hide:all; } #maincontent td{color:#525C65;} </style> </head> <body bgcolor=\"#fafbfc\" style=\"Margin:0; padding:0;\" yahoo=\"fix\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tbody><tr> <td style=\"background-color:#fafbfc\"> <center bgcolor=\"#fafbfc\" style=\"width:100%;background-color:#fafbfc;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;\"> <div id=\"maincontent\" style=\"max-width:620px; font-size:0;margin:0 auto;\"> <div class=\"preheader\" style=\"font-size: 1px; line-height:1px; display: none!important; mso-hide:all;\"> One more step to get started </div> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%;\"> <tbody><tr> <td> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%;\"> <tbody><tr> <td align=\"center\" style=\"padding-bottom:20px;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family:'Open+Sans', 'Open Sans', Helvetica, Arial, sans-serif; font-size:13px; line-height:18px; color:#00C0EA; text-align:center; width:152px;\"> <tbody><tr> <td style=\"padding:20px 0 10px 0;\"> <a href=\"#tba\" style=\"text-decoration:none;\" target=\"_blank\"><img alt=\"Udacity\" border=\"0\" height=\"27\" src=\"https://salt.tikicdn.com/cache/w750/ts/banner/33/c6/5d/ed0648ae67b0024e4a1258e3fafebbc5.jpg\" style=\"display:block; width:152px !important; font-family:'Open+Sans', 'Open Sans', Helvetica, Arial, sans-serif; font-size:22px; line-height:26px; color:#000000; text-transform:uppercase; text-align:center; letter-spacing:1px;\" width=\"152\"></a> </td> </tr> </tbody></table> </td> </tr> </tbody></table> </td> </tr> <tr> <td> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%;\"> <tbody><tr> <td bgcolor=\"#fafbfc\" style=\"width:7px; font-size:1px;\">&nbsp;</td> <td bgcolor=\"#f5f6f7\" style=\"width:1px; font-size:1px;\">&nbsp;</td> <td bgcolor=\"#f0f2f3\" style=\"width:1px; font-size:1px;\">&nbsp;</td> <td bgcolor=\"#edeef1\" style=\"width:1px; font-size:1px;\">&nbsp;</td> <td bgcolor=\"#ffffff\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%;\"> <tbody><tr> <td style=\"text-align:center; padding:40px 40px 40px 40px; border-top:3px solid #02b3e4;\"> <div style=\"display:inline-block; width:100%; max-width:520px;\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family:'Open+Sans', 'Open Sans', Helvetica, Arial, sans-serif; font-size:14px; line-height:24px; color:#525C65; text-align:left; width:100%;\"> <tbody><tr> <td> <p style=\"Margin:0; font-size:18px; line-height:23px; color:#102231; font-weight:bold;\"> <strong> Chào Bạn,</strong><br><br> </p> </td> </tr> <tr> <td> Để hoàn tất đăng ký, vui lòng xác minh email của bạn: <br><br> </td> </tr> <tr> <td align=\"center\" style=\"padding:15px 0 40px 0; border-bottom:1px solid #f3f6f9; \"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse:separate !important; border-radius:15px; width:210px;\"> <tbody><tr> <td align=\"center\" valign=\"top\"> <a href=\""+link+ "\"target=\"_blank\" style=\"background-color:#01b3e3; border-collapse:separate !important; border-top:10px solid #01b3e3; border-bottom:10px solid #01b3e3; border-right:45px solid #01b3e3; border-left:45px solid #01b3e3; border-radius:4px; color:#ffffff; display:inline-block; font-family:'Open+Sans','Open Sans',Helvetica, Arial, sans-serif; font-size:13px; font-weight:bold; text-align:center; text-decoration:none; letter-spacing:2px;\">XÁC NHẬN</a> </td> </tr> </tbody></table> </td> </tr> <tr> <td style=\"padding-top:30px;\"> <p style=\"Margin:20px 0 20px 0;\">Hoặc sao chép liên kết này và dán vào trình duyệt web của bạn</p> <p style=\"Margin:20px 0; font-size:12px; line-height:17px; word-wrap:break-word; word-break:break-all;\"><a href=\""+link+"\" style=\"color:#5885ff; text-decoration:underline;\" target=\"_blank\">"+link+"\"</a></p> </td> </tr> <tr> <td style=\"font:bold 14px/16px Arial, Helvetica, sans-serif; color:#363636; padding:0 0 7px;\"> Cảm ơn bạn. </td> </tr> </tbody></table> </div> </td> </tr> <tr> <td bgcolor=\"#e0e2e5\" style=\"height:1px; width:100%; line-height:1px; font-size:0;\">&nbsp;</td> </tr> <tr> <td bgcolor=\"#e0e2e4\" style=\"height:1px; width:100%; line-height:1px; font-size:0;\">&nbsp;</td> </tr> <tr> <td bgcolor=\"#e8ebed\" style=\"height:1px; width:100%; line-height:1px; font-size:0;\">&nbsp;</td> </tr> <tr> <td bgcolor=\"#f1f3f6\" style=\"height:1px; width:100%; line-height:1px; font-size:0;\">&nbsp;</td> </tr> </tbody></table> </td> <td bgcolor=\"#edeef1\" style=\"width:1px; font-size:1px;\">&nbsp;</td> <td bgcolor=\"#f0f2f3\" style=\"width:1px; font-size:1px;\">&nbsp;</td> <td bgcolor=\"#f5f6f7\" style=\"width:1px; font-size:1px;\">&nbsp;</td> <td bgcolor=\"#fafbfc\" style=\"width:7px; font-size:1px;\">&nbsp;</td> </tr> </tbody></table> </td> </tr> </body";
            return tam;
        }

        public string mailDonDatHang(TaoDonHang donHang, int maDonHang, KhachHang kh)
        {
            string tenKhachHang = "", ngayDatHang = "", email = "", soDT = "", diaChi = "", ngayGiaoHang = "", phiVanCHuyen = "", tongTamTinh = "", tongTien = "";
            DiaChiKhachHang dc = db.DiaChiKhachHangs.FirstOrDefault(e => e.id == donHang.idNoiNhan);
            TinhThanh tinh = db.TinhThanhs.FirstOrDefault(x => x.ma_tinh == dc.id_tinh);
            QuanHuyen quan = db.QuanHuyens.FirstOrDefault(x => x.ma_quan_huyen == dc.id_quan);
            XaPhuong xa = db.XaPhuongs.FirstOrDefault(x => x.ma_xa_phuong == dc.id_xa_phuong);
            diaChi = "" + dc.dia_chi + ", " + xa.ten + ", " + quan.ten_quan_huyen + ", " + tinh.ten;
            tenKhachHang = kh.ten_nguoi_dung;
            email = kh.email;
            soDT = donHang.soDT.ToString();
            ngayDatHang = donHang.ngayLap.ToString("dd/MM/yyyy");
            phiVanCHuyen = "0";
            tongTamTinh = string.Format("{0:N}", donHang.tongTien); 
            tongTien = string.Format("{0:N}", donHang.tongTien);

            string t1 = "<tbody> <tr> <td align=\"center\" style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal\" valign=\"top\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-top:15px\" width=\"600\"> <tbody> <tr style=\"background:#fff\"> <td align=\"left\" height=\"auto\" style=\"padding:15px\" width=\"600\"> <table> <tbody> <tr> <td> <h1 style=\"font-size:17px;font-weight:bold;color:#444;padding:0 0 5px 0;margin:0\">Cảm ơn quý khách "+tenKhachHang+" đã đặt hàng tại VTNshop,</h1> <p style=\"margin:4px 0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal\">VTNshop rất vui thông báo đơn hàng #"+maDonHang+" của quý khách đã được tiếp nhận và đang trong quá trình xử lý. VTNshop sẽ thông báo đến quý khách ngay khi hàng chuẩn bị được giao.</p> <h3 style=\"font-size:13px;font-weight:bold;color:#02acea;text-transform:uppercase;margin:20px 0 0 0;border-bottom:1px solid #ddd\">Thông tin đơn hàng #"+maDonHang+" <span style=\"font-size:12px;color:#777;text-transform:none;font-weight:normal\">(Ngày "+ngayDatHang+")</span></h3> </td> </tr>";
            string t2 = " <tr> <td style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px\"> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <thead> <tr> <th align=\"left\" style=\"padding:6px 9px 0px 9px;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;font-weight:bold\" width=\"50%\">Thông tin thanh toán</th> <th align=\"left\" style=\"padding:6px 9px 0px 9px;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;font-weight:bold\" width=\"50%\"> Địa chỉ giao hàng </th> </tr> </thead> <tbody> <tr> <td style=\"padding:3px 9px 9px 9px;border-top:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal\" valign=\"top\"><span style=\"text-transform:capitalize\">"+tenKhachHang+"</span><br> <a target=\"_blank\">"+email+"</a><br> 0"+soDT+"</td> <td style=\"padding:3px 9px 9px 9px;border-top:0;border-left:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal\" valign=\"top\"><span style=\"text-transform:capitalize\">"+tenKhachHang+"</span><br> <a target=\"_blank\">"+email+"</a><br> "+diaChi+", Việt Nam<br> T: 0"+soDT+"</td> </tr>";
            string t3 = "<tr> <td colspan=\"2\" style=\"padding:7px 9px 0px 9px;border-top:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444\" valign=\"top\"> <p style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal\"><strong>Phương thức thanh toán: </strong> Thanh toán tiền mặt khi nhận hàng<br> <strong>Thời gian giao hàng dự kiến:</strong> Dự kiến giao hàng "+ngayGiaoHang+" <br> <strong>Phí vận chuyển: </strong> "+phiVanCHuyen+"&nbsp;₫<br> <strong>Sử dụng bọc sách cao cấp Bookcare: </strong> Không <br> </p> </td> </tr> </tbody> </table> </td> </tr> <tr> <td> <p style=\"margin:4px 0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal\"><i>Lưu ý: Đối với đơn hàng đã được thanh toán trước, nhân viên giao nhận có thể yêu cầu người nhận hàng cung cấp CMND / giấy phép lái xe để chụp ảnh hoặc ghi lại thông tin.</i></p> </td> </tr> <tr> <td> <h2 style=\"text-align:left;margin:10px 0;border-bottom:1px solid #ddd;padding-bottom:5px;font-size:13px;color:#02acea\">CHI TIẾT ĐƠN HÀNG</h2>";
            string t4 = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#f5f5f5\" width=\"100%\"> <thead> <tr> <th align=\"left\" bgcolor=\"#02acea\" style=\"padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px\">Sản phẩm</th> <th align=\"left\" bgcolor=\"#02acea\" style=\"padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px\">Đơn giá</th> <th align=\"left\" bgcolor=\"#02acea\" style=\"padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px\">Số lượng</th> <th align=\"left\" bgcolor=\"#02acea\" style=\"padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px\">Giảm giá</th> <th align=\"right\" bgcolor=\"#02acea\" style=\"padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px\">Tổng tạm</th> </tr> </thead>";
            string t5 = "";
            string t6 = "<tfoot style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px\"><tr> <td align=\"right\" colspan=\"4\" style=\"padding:5px 9px\">Tổng tạm tính</td> <td align=\"right\" style=\"padding:5px 9px\"><span>"+tongTamTinh+"&nbsp;₫</span></td> </tr> <tr> <td align=\"right\" colspan=\"4\" style=\"padding:5px 9px\">Phí vận chuyển</td> <td align=\"right\" style=\"padding:5px 9px\"><span>"+phiVanCHuyen+"&nbsp;₫</span></td> </tr> <tr bgcolor=\"#eee\"> <td align=\"right\" colspan=\"4\" style=\"padding:7px 9px\"><strong><big>Tổng giá trị đơn hàng</big> </strong></td> <td align=\"right\" style=\"padding:7px 9px\"><strong><big><span>"+tongTien+"&nbsp;₫</span> </big> </strong></td> </tr> </tfoot> </table> </tr>";
            string t7 = "<tr> <td>&nbsp; <p style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal;border:1px #14ade5 dashed;padding:5px;list-style-type:none\">Từ ngày 14/5/2019, VTNshop sẽ không gởi tin nhắn SMS khi đơn hàng của bạn được xác nhận thành công. Chúng tôi chỉ liên hệ trong trường hợp đơn hàng có thể bị trễ hoặc không liên hệ giao hàng được.</p> <p style=\"margin:10px 0 0 0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal\">Bạn cần được hỗ trợ ngay? Chỉ cần email <a style=\"color:#099202;text-decoration:none\" target=\"_blank\"> <strong>emailKhongtontai@fake.vn</strong> </a>, hoặc gọi số điện thoại <strong style=\"color:#099202\">0971434XXX</strong> (10-15h T2 đến T6). Admin giám đốc luôn sẵn sàng hỗ trợ bạn bất kì lúc nào(tuy tâm trạng nhé).</p> </td> </tr> <tr> <td>&nbsp; <p style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;margin:0;padding:0;line-height:18px;color:#444;font-weight:bold\">Một lần nữa VTNshop cảm ơn quý khách.</p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table> </td> </tr> <tr> <td align=\"center\"> <table width=\"600\"> <tbody> <tr> <td> <p align=\"left\" style=\"font-family:Arial,Helvetica,sans-serif;font-size:11px;line-height:18px;color:#4b8da5;padding:10px 0;margin:0px;font-weight:normal\">Quý khách nhận được email này vì đã mua hàng tại VTNshop.<br> Để chắc chắn luôn nhận được email thông báo, xác nhận mua hàng từ VTNshop, quý khách vui lòng thêm địa chỉ <strong><a target=\"_blank\">hotrolienheadminnhe@VTN.vn</a></strong> vào số địa chỉ (Address Book, Contacts) của hộp email.<br> <b>Văn phòng VTNshop:</b> 97 Man Thiện, phường Hiệp Phú, quận 9, thành phố Hồ Chí Minh, Việt Nam<br> </p> </td> </tr> </tbody> </table> </td> </tr></tbody>";

            for (int i = 0; i < donHang.danhSachSanPham.Count(); i++)
            {
                SanPham sp = db.SanPhams.FirstOrDefault(e => e.id_san_pham == donHang.danhSachSanPham[i].idSanPham);
                string donGia = string.Format("{0:N}", sp.gia_km);
                string tongDongia = string.Format("{0:N}", donHang.danhSachSanPham[i].tongGia);
                string tam = "<tbody bgcolor=\"#eee\" style=\"font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px\"><tr> <td align=\"left\" style=\"padding:3px 9px\" valign=\"top\"><span class=\"m_6040208975790204668name\">"+sp.ten_sp+"</span><br> </td> <td align=\"left\" style=\"padding:3px 9px\" valign=\"top\"><span>"+ donGia +"&nbsp;₫</span></td> <td align=\"left\" style=\"padding:3px 9px\" valign=\"top\">"+donHang.danhSachSanPham[i].soLuong.ToString() +"</td> <td align=\"left\" style=\"padding:3px 9px\" valign=\"top\"><span>0&nbsp;₫</span></td> <td align=\"right\" style=\"padding:3px 9px\" valign=\"top\"><span>"+ tongDongia +"&nbsp;₫</span></td> </tr> </tbody>";
                t5 = t5 + tam;
            }
            return t1+t2+t3+t4+t5+t6+t7;
        }
        
        public static string Encrypt(string toEncrypt)
        {
            string key = "vtnshop";
            bool useHashing = true;
            byte[] keyArray;
            byte[] toEncryptArray = UTF8Encoding.UTF8.GetBytes(toEncrypt);

            if (useHashing)
            {
                MD5CryptoServiceProvider hashmd5 = new MD5CryptoServiceProvider();
                keyArray = hashmd5.ComputeHash(UTF8Encoding.UTF8.GetBytes(key));
            }
            else
                keyArray = UTF8Encoding.UTF8.GetBytes(key);

            TripleDESCryptoServiceProvider tdes = new TripleDESCryptoServiceProvider();
            tdes.Key = keyArray;
            tdes.Mode = CipherMode.ECB;
            tdes.Padding = PaddingMode.PKCS7;

            ICryptoTransform cTransform = tdes.CreateEncryptor();
            byte[] resultArray = cTransform.TransformFinalBlock(toEncryptArray, 0, toEncryptArray.Length);

            return Convert.ToBase64String(resultArray, 0, resultArray.Length);
        }
       
    }
}