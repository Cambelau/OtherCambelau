#ifndef COORD_H_INCLUDED
#define COORD_H_INCLUDED
#include <iostream>

class Coord
{
private:
    double m_x;
    double m_y;

public:
    Coord();
    ~Coord();
    double get_x()const;
    double get_y()const;
    void set_x(double var);
    void set_y(double var);

};


#endif // COORD_H_INCLUDED
