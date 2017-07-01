package projectHotelManagement.data;

/**
 * Class that describe Additional services
 * @author Nemanja
 *
 */
public class AdditionalServices {

	private int servicesId;
	private int gymPrice = 10;
	private int cinemaPrice = 10;
	private int restaurantPrice = 20;
	private int swimmingPoolPrice = 10;
	private int saunaPrice = 10;
	
	public int getServicesId() {
		return servicesId;
	}

	public void setServicesId(int servicesId) {
		this.servicesId = servicesId;
	}

	public int getGymPrice() {
		return gymPrice;
	}

	public void setGymPrice(int gymPrice) {
		this.gymPrice = gymPrice;
	}

	public int getCinemaPrice() {
		return cinemaPrice;
	}

	public void setCinemaPrice(int cinemaPrice) {
		this.cinemaPrice = cinemaPrice;
	}

	public int getRestaurantPrice() {
		return restaurantPrice;
	}

	public void setRestaurantPrice(int restaurantPrice) {
		this.restaurantPrice = restaurantPrice;
	}

	public int getSwimmingPoolPrice() {
		return swimmingPoolPrice;
	}

	public void setSwimmingPoolPrice(int swimmingPoolPrice) {
		this.swimmingPoolPrice = swimmingPoolPrice;
	}

	public int getSaunaPrice() {
		return saunaPrice;
	}

	public void setSaunaPrice(int saunaPrice) {
		this.saunaPrice = saunaPrice;
	}
	
	
}
