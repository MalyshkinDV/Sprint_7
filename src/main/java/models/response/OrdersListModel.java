package models.response;

import models.Model;

public class OrdersListModel implements Model {
    private OrdersModel[] orders;
    private PageInfoModel pageInfo;
    private AvailableStationsModel[] availableStations;

    public OrdersModel[] getOrders() {
        return orders;
    }

    public void setOrders(OrdersModel[] orders) {
        this.orders = orders;
    }

    public PageInfoModel getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoModel pageInfoModel) {
        this.pageInfo = pageInfoModel;
    }

    public AvailableStationsModel[] getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(AvailableStationsModel[] availableStationsModels) {
        this.availableStations = availableStationsModels;
    }
}
