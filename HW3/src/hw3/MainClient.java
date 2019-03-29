package edu.umb.cs.cs681.hw3;

import java.util.ArrayList;

public class MainClient {

	public static void main(String[] args) {
		
		ArrayList<Car> usedCars = new ArrayList<Car>();
		
		usedCars.add(new Car(40000,2018,10000,"BMW"));
		usedCars.add(new Car(40000,2012,1050000,"BMW"));
		usedCars.add(new Car(50000,2010,50000,"HONDA"));
		usedCars.add(new Car(20000,2017,30000,"HAUNDAI"));
		usedCars.add(new Car(10000,2003,75000,"FERRARI"));
		usedCars.add(new Car(10000,2018,750,"MERCEDES"));
		usedCars.add(new Car(40000,2018,750,"MERCEDES"));
		
		System.out.println("\n Inserted Car Price are: \n");
		usedCars.forEach( (Car car)->System.out.println(car.getPrice()));
		System.out.println("\n \n HW3-1 min max count with reduce : ");
				
		// Count reduce
				
				Integer Count = usedCars.stream().map( (Car car)-> car.getPrice() )
						. reduce(0, (result, carPrice)->{return ++result;} );
				System.out.println("\n Total car are: " +Count);
		
		// Max reduce
		Integer maxPrice = usedCars.stream().map( (Car car)-> car.getPrice() )
				. reduce(0, (result, carPrice)->{
							if(result==0) return carPrice;
							else if(carPrice < result) return result;
							else return carPrice;} );
		System.out.println("\n \n Maximum car price is : " +maxPrice);
		
		//Min reduce
		
		Integer minPrice = usedCars.stream().map( (Car car)-> car.getPrice() )
												. reduce(0, (result, carPrice)->{
															if(result==0) return carPrice;
															else if(carPrice < result) return carPrice;
															else return result;} );
		System.out.println("\n \n Minimum car price is : " +minPrice);

				
		//HW3-2 average with reduce...............
				System.out.println("\n HW3-2 average car price with the 3rd version of reduce() \n");
				int[] avgArr = usedCars.stream().map((Car car)-> car.getPrice() )
						. reduce(new int[3],
								(arr, price)->{arr[0] = 1+arr[0];
												arr[1]=price+ arr[1];
												if(arr[0]!=0)
													arr[2] = arr[1]/arr[0];
									return arr; },
									(arr, intermediateArr)->{ return arr; });
				System.out.println("\n CAR PRice: "+avgArr[1]);
				System.out.println("\n Number of Cars are: "+ avgArr[0]);
				System.out.println("\n Average of car price is: " +avgArr[2]);
				
	}
			
}
