/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhltn.dtos;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leagu
 */
@XmlRootElement
public class HistoryOfUserListDTO implements Serializable {

    private List<HistoryOfUserDTO> list;

    public HistoryOfUserListDTO() {
    }

    public HistoryOfUserListDTO(List<HistoryOfUserDTO> list) {
        this.list = list;
    }

    @XmlElement
    public List<HistoryOfUserDTO> getList() {
        return list;
    }

    public void setList(List<HistoryOfUserDTO> list) {
        this.list = list;
    }

}
