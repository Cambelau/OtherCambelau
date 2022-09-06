#include "coord.h"

Coord::Coord()
{

}

Coord::~Coord()
{

}

double Coord::get_x()const
{
    return m_x;
}

double Coord::get_y()const
{
    return m_y;
}

void Coord::set_x(double var)
{
    m_x = var;
}

void Coord::set_y(double var)
{
    m_y = var;
}
