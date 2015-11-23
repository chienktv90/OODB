/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbOject;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author chienktv90
 */
public class Buucuc {
        private String IdBuuCuc;
        private String TenBuuCuc;
        private String DiaChi;
        //khong them so mac trinh cac thuoc tinh la null
        public Buucuc()
        {
        }
        public Buucuc(String id)
        {
            this.IdBuuCuc = id;
            this.TenBuuCuc = null;
            this.DiaChi = null;
        }


        public Buucuc(String id, String ten, String dc)
        {
            this.IdBuuCuc = id;
            this.TenBuuCuc = ten;
            this.DiaChi = dc;
        }

        public String getIdBuuCuc()
        {
          return this.IdBuuCuc;
        }

        public void setIdBuuCuc(String id)
        {
            this.IdBuuCuc=id;
        }

        public String getTenBuuCuc()
        {
          return this.TenBuuCuc;
        }

        public void setTenBuuCuc(String ten)
        {
            this.TenBuuCuc=ten;
        }
        
        public String getDiaChi()
        {
          return this.DiaChi;
        }

        public void setDiaChi(String dc)
        {
            this.DiaChi=dc;
        }
}
